/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.hcl.seekin.Util

import org.grails.grailsui.TabTagLib

class YUITabTagLib extends TabTagLib {

    def tab = {attrs, body ->
        attrs = grailsUITagLibService.establishDefaultValues(
                [
                        id: grailsUITagLibService.getUniqueId(),
                        active: false,
                        cached: false
                ],
                attrs,
                ['label']
        )

        if (attrs.controller && attrs.action) {
            def id = attrs.remove('id')
            attrs.dataSrc = createLink(attrs)   // set dynamic dataSrc if necessary
            attrs.id = id
        }
        def classes = "class='${attrs.active ? 'selected' : ''} ${attrs['class'] ? attrs['class'] : ''} ${attrs.disabled ? 'disabled' : ''}'"
        if ((attrs.controller && attrs.action) || attrs.dataSrc) {
            // dynamically rendered config
            tabs.get()[getCurrentTabViewId()] << [config : attrs]
        } else {
            def nav = """<li ${classes}><a href="#${attrs.id}"><span class="tabLeft"></span><em id="label_${attrs.id}">${attrs.label}</em><span class="tabRight"></span></a></li>\n"""
            def content = """<div id="${attrs.id}">${body()}</div>\n"""
            tabs.get()[getCurrentTabViewId()] << [nav:nav, content:content]
        }
    }

}