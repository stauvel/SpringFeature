package fr.stauvel.feature.taglib;

import fr.stauvel.feature.tools.FeatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag to add a Json features in the DOM.
 * The Javascript below can be used to test if a feature is enabled :
 * if(features.myFeature){...}
 */
public class FeaturesJsonTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(FeaturesJsonTag.class);

    @Override
    public int doStartTag() throws JspException {
        try {
            String features = FeatureUtil.getJsonEnabledFeatures();
            pageContext.getOut().print(String.format("<script type=\"text/javascript\">var features = %s</script>", features));
        } catch (Exception e) {
            LOG.error("Unexpected error while listing enabled features in a view.", e);
        }
        return SKIP_BODY;
    }
}
