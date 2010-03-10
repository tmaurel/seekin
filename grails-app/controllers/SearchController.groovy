/*
 * Copyright 2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.compass.core.engine.SearchEngineQueryParseException
import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Internship.Internship
import me.hcl.seekin.Company


/**
 * Basic web interface for Grails Searchable Plugin
 *
 * @author Maurice Nicholson
 */
class SearchController {
    def searchableService
	def authenticateService

    /**
     * Index page with search form and results
     */
    def search = {
		if(authenticateService.isLoggedIn())
		{
			def searchResult

			if (params.q?.trim()) {
				try {
					Closure qb = {
						queryString(params.q)
						// TODO Add search permission for the other ROLE
						if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
							/* Only students who are set their profile as visible */
							mustNot(term('visible',false))
							/* Only internships whitch have been approved */
							mustNot(term('isApproval',false))
						}
						if(authenticateService.ifAnyGranted("ROLE_EXTERNAL")) {
							/* External can not search internships */
							mustNot(alias('Internship'))
							mustNot(alias('Offer'))
						}
					}

					if(params.domain)
					{
						switch(params.domain) {
							case 'all':
								searchResult = searchableService.search(qb)
								break
							case 'student':
								searchResult = Student.search(qb)
								break
							case 'internship':
								searchResult = Internship.search(qb)
								break
							case 'company':
								println 'toto'
								searchResult = Company.search(qb)
								break
							default:
								searchResult = searchableService.search(qb)
								break
						}
					}
					else
					{
						searchResult = searchableService.search(qb)
					}

				} catch (SearchEngineQueryParseException ex) {
					return [parseException: true]
				}
			}
			render(
				view: 'search',
				model: [
					searchableDomain: ['all','student','internship','company'],
					searchResult: searchResult,
				]
			)
		}
		else {
			redirect(controller:'user', action:'auth')
		}
    }
}