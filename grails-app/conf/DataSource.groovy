/* dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
*/
dataSource {
	pooled = false
}

hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}

// environment specific settings
environments {
	development {
		dataSource {
			driverClassName = "org.hsqldb.jdbcDriver"
			dbCreate = "create-drop"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
	test {
		dataSource {
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = ""
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://localhost/seekin_test"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}