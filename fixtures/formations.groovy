import me.hcl.seekin.Formation.*

include "millesimes"

fixture {
	m2glia(Formation, label : "M2 GLIA", description : "Master 2 Genie Logiciel et Intégration d'Applications")
	m2siad(Formation, label : "M2 SIAD", description : "Master 2 Systèmes d'Information et Aide à la Décision")

        siad2008(Promotion, millesime: m2008, formation: m2siad)
        glia2008(Promotion, millesime: m2008, formation: m2glia)
        glia2009(Promotion, millesime: m2009, formation: m2glia)
        siad2009(Promotion, millesime: m2009, formation: m2siad)
}
