package me.hcl.seekin.Ressource

class DocumentController {
	def scaffold = true

        static navigation = [
		group:'menu',
		order:2,
		title:'document',
		action:'index'
	]

}
