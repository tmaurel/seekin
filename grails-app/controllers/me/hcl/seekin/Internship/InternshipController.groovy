package me.hcl.seekin.Internship

class InternshipController {
  
	def scaffold = true

    def dataTableDataAsJSON = {
        def list = []
        response.setHeader("Cache-Control", "no-store")
        def data = [
                totalRecords: Country.count(),
                results: Country.list(params)
        ]
        println data.results
        render data as JSON
    }
}
