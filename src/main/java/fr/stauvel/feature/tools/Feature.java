package fr.stauvel.feature.tools;

/**
 * All managed profiles are listed below
 */
public enum Feature {
    FEATURE_1("feature1"),
    FEATURE_2("feature2"),
    DEV_MODE("dev");
    private final String name;

    Feature(String name) {
        this.name = name;
    }

    public static Feature getByName(String name) {
        for (Feature feature : Feature.values()) {
            if (feature.getName().equals(name.trim())) {
                return feature;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
