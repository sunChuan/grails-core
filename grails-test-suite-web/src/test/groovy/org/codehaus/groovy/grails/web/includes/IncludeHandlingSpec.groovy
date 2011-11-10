package org.codehaus.groovy.grails.web.includes

import spock.lang.Specification
import grails.test.mixin.TestMixin
import grails.test.mixin.web.GroovyPageUnitTestMixin
import org.springframework.web.util.WebUtils

/**
 * Tests the behavior of the include tag
 */

@TestMixin(GroovyPageUnitTestMixin)
class IncludeHandlingSpec extends Specification{


    void "Test the appropriate request headers are set and URI of a page included"() {
        given:"A template that includes a view"
            views['/foo/_bar.gsp'] = "Include = <g:include view='/foo/include.gsp'/>"

        when:"The template is rendered"
            def content = render(template:"/foo/bar")

        then:"The include status is valid"
            content == "Include = "
            response.includedUrls
            response.includedUrls[0] == '/foo/include.gsp'
            request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE) == null
            request.getAttribute(WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE) == null
            request.getAttribute(WebUtils.INCLUDE_PATH_INFO_ATTRIBUTE) == null
            request.getAttribute(WebUtils.INCLUDE_QUERY_STRING_ATTRIBUTE) == null
            request.getAttribute(WebUtils.INCLUDE_SERVLET_PATH_ATTRIBUTE) == null

    }

}