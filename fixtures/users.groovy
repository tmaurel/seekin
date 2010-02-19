import me.hcl.seekin.Auth.User
import me.hcl.seekin.Util.Address

include "externals"
include "staffs"
include "students"

fixture {

    nadirAddress(Address, street : "12 chemin floreal", zipCode : "63000", town : "Clermont-Ferrand")
    nadir(
        User,
        email : "nadir.boukeffa@gmail.com" ,
        password : authenticateService.encodePassword("nadir"),
        enabled : "true",
        firstName : "Nadir",
        lastName : "Boukeffa",
        address : nadirAddress ,
        phone : "0102030405",
        authorities : [nadirRole],
		showEmail : true
    )


    alexisAddress(Address, street : "12 rue des Riveaux", zipCode : "63320", town : "Chidrac")
    alexis(
        User,
        email : "alexis.plantin@gmail.com" ,
        password : authenticateService.encodePassword("alexis"),
        enabled : "true",
        firstName : "Alexis", 
        lastName : "Plantin" , 
        address : alexisAddress, 
        phone : "0102030405",
        authorities : [alexisRole],
		showEmail : false

    )

    thomasAddress(Address, street : "12 chemin floreal", zipCode : "63110", town : "Beaumont")
    thomas(
        User,
        email : "neoseifer@gmail.com" ,
        password : authenticateService.encodePassword("thomas"),
        enabled : "true",
        firstName : "Thomas",
        lastName : "Maurel" ,
        address : thomasAddress,
        phone : "0102030405",
        authorities : [thomasRole],
		showEmail : true
    )

    balmerAddress(Address, street : "1 Microsoft Way", zipCode : "98052", town : "Washington")
    balmer(
        User,
        email : "steve.balmer@gmail.com" ,
        password : authenticateService.encodePassword("steve"),
        enabled : "true",
        firstName : "Steve",
        lastName : "Balmer" ,
        address : balmerAddress,
        phone : "0102030405",
        authorities : [balmerRole]
    )

    jobsAddress(Address, street : "1 Infinite Loop", zipCode : "95014", town : "Cupertino")
    jobs(
        User,
        email : "steve.jobs@gmail.com" ,
        password : authenticateService.encodePassword("steve"),
        enabled : "true",
        firstName : "Steve",
        lastName : "Jobs" ,
        address : jobsAddress,
        phone : "0102030405",
        authorities : [jobsRole]
    )

    hclAddress(Address, street : "1 rue hcl senior", zipCode : "98052", town : "Washington")
    hcl(
        User,
        email : "hcl@hcl.me",
        password : authenticateService.encodePassword("hcl"),
        enabled : "true",
        firstName : "Senior",
        lastName : "Hcl",
        address : hclAddress,
        phone : "0102030405",
        authorities : [hclRole]
    )
}