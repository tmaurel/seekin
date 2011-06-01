/* dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
*/
dataSource {
	pooled = false
        driverClassName = "com.mysql.jdbc.Driver"
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
			url = "jdbc:hsqldb:file:proDb;shutdown=true"
		}
	}
	test {
		dataSource {
                        driverClassName = "org.hsqldb.jdbcDriver"
			dbCreate = "create-drop"
			url = "jdbc:hsqldb:file:proDb;shutdown=true"
		}
	}
	production {
		dataSource {
                        username = "root"
                        password = "root"
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://localhost/seekin"
		}
	}
}
