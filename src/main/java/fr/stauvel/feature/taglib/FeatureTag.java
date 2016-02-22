package fr.stauvel.feature.taglib;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Enable/disable jsp fragment if spring-profile is enabled/disabled
 */
public class FeatureTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String profile;

    private String activated = "true";

    private boolean isActivated() {
        return "true".equals(activated);
    }

    @Override
    public int doStartTag() throws JspException {
        String[] activeProfiles = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext()).getEnvironment().getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            if (activeProfile.equals(profile)) {
                return isActivated() ? EVAL_BODY_INCLUDE : SKIP_BODY;
            }
        }
        return isActivated() ? SKIP_BODY : EVAL_BODY_INCLUDE;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
