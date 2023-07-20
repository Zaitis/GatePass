package com.sk.GatePass.view.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sk.GatePass.security.SecurityService;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.internal.NullOwner;
import com.vaadin.flow.internal.StateNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserLayout.class, SecurityService.class})
@ExtendWith(SpringExtension.class)
class UserLayoutTest {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserLayout userLayout;

    /**
     * Method under test: {@link UserLayout#UserLayout(SecurityService)}
     */
    @Test
    void testConstructor() {
        UserLayout actualUserLayout = new UserLayout(securityService);
        assertFalse(actualUserLayout.isAttached());
        Element element = actualUserLayout.getElement();
        assertFalse(element.isTextNode());
        assertEquals("Parking ManagerLog outDashboardAdd CarMake Gate PassUpdate Personal Data",
                element.getTextRecursively());
        assertEquals("", element.getText());
        assertEquals("vaadin-app-layout", element.getTag());
        assertEquals("<vaadin-app-layout>\n"
                + " <vaadin-horizontal-layout theme=\"spacing\" slot=\"navbar\" style=\"width:100%;align-items:center\" class=\"py-0"
                + " px-m\">\n" + "  <vaadin-drawer-toggle></vaadin-drawer-toggle>\n"
                + "  <h1 style=\"flex-grow:1.0\" class=\"text-l m-m\">Parking Manager</h1>\n" + "  <vaadin-button>\n"
                + "   Log out\n" + "  </vaadin-button>\n" + " </vaadin-horizontal-layout>\n"
                + " <vaadin-vertical-layout theme=\"padding spacing\" slot=\"drawer\" style=\"width:100%\">\n"
                + "  <vaadin-button>\n" + "   Dashboard\n" + "  </vaadin-button>\n" + "  <vaadin-button style=\"width:200\">\n"
                + "   Add Car\n" + "  </vaadin-button>\n" + "  <vaadin-button>\n" + "   Make Gate Pass\n"
                + "  </vaadin-button>\n" + "  <vaadin-button>\n" + "   Update Personal Data\n" + "  </vaadin-button>\n"
                + " </vaadin-vertical-layout>\n" + "</vaadin-app-layout>", element.getOuterHTML());
        assertTrue(element.getComponent().isPresent());
        assertEquals(2, element.getChildCount());
        assertTrue(element.isVisible());
        StateNode node = element.getNode();
        assertTrue(node.getOwner() instanceof NullOwner);
        assertEquals(-1, node.getId());
        assertTrue(node.isEnabledSelf());
        assertFalse(node.isInert());
    }
}

