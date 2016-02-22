package fr.stauvel.feature.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Util class for checking specific spring-profile activation in Java and Javascript
 */
public class FeatureUtil {

    final static Logger LOG = LoggerFactory.getLogger(FeatureUtil.class);

    /**
     * Enabled spring-profiles EnumSet
     */
    private static EnumSet<Feature> enabledFeatures;
    /**
     * Enabled spring-profiles JSON representation
     */
    private static String jsonEnabledFeatures;

    /**
     * Test if a managed spring-profile is enabled or not
     *
     * @param feature spring-profile managed as a feature
     * @return 'true' is the feature is enabled
     */
    public static boolean check(Feature feature) {
        return enabledFeatures.contains(feature);
    }

    /**
     * Send a JSON representation of enabled features
     *
     * @return
     */
    public static String getJsonEnabledFeatures() {
        return jsonEnabledFeatures;
    }

    static {
        parseActivatedFeatures();
        jsonifyfeatures();
    }

    private static void parseActivatedFeatures() {
        //Looking for enabled spring-profiles
        String profilesProperty = System.getProperty("spring.profiles.active");
        if (profilesProperty == null || profilesProperty.isEmpty()) {
            //No feature enabled
            enabledFeatures = EnumSet.noneOf(Feature.class);
        } else {
            String[] enabledSpringProfiles = profilesProperty.split(",");
            //Matching enabled spring-profiles with managed features
            List<Feature> enabledManagedFeatures = new ArrayList<>(enabledSpringProfiles.length);
            for (String enabledSpringProfile : enabledSpringProfiles) {
                Feature matchingFeature = Feature.getByName(enabledSpringProfile);
                if (matchingFeature == null) {
                    LOG.warn("The spring-profile '{0}' was enabled but not managed as a feature.", enabledSpringProfile);
                } else {
                    enabledManagedFeatures.add(matchingFeature);
                }
            }
            //Convert to enumSet for performance
            enabledFeatures = EnumSet.copyOf(enabledManagedFeatures);
        }
    }

    private static void jsonifyfeatures() {
        StringBuilder jsonFeatures = new StringBuilder("{");
        for (Feature feature : enabledFeatures) {
            jsonFeatures.append(String.format("\"%s\":%b,", feature.getName(), true));
        }
        //replace last comma
        int length = jsonFeatures.length();
        if (length > 1) {
            jsonFeatures.replace(length - 1, length, "}");
        } else {
            jsonFeatures.append("}");
        }
        jsonEnabledFeatures = jsonFeatures.toString();
    }

}
