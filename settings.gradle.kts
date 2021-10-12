/*
 * Copyright 2014-2020 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin2js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "ktor"

val native_targets_enabled = !extra.has("disable_native_targets")
val CACHE_USER = System.getenv("GRADLE_CACHE_USER")

if (CACHE_USER != null) {
    val CACHE_PASSWORD = System.getenv("GRADLE_CACHE_PASSWORD")
    buildCache {
        remote(HttpBuildCache::class) {
            isPush = true
            setUrl("https://ktor-gradle-cache.teamcity.com/cache/")
            credentials {
                username = CACHE_USER
                password = CACHE_PASSWORD
            }
        }
    }
}

val fullVersion = System.getProperty("java.version", "8.0.0")
val versionComponents = fullVersion
    .split(".")
    .take(2)
    .filter { it.isNotBlank() }
    .map { Integer.parseInt(it) }

val currentJdk = if (versionComponents[0] == 1) versionComponents[1] else versionComponents[0]

include(":ktor-server")
include(":ktor-server:ktor-server-core")
include(":ktor-server:ktor-server-tests")
include(":ktor-server:ktor-server-host-common")
include(":ktor-server:ktor-server-test-host")
include(":ktor-server:ktor-server-test-suites")
include(":ktor-server:ktor-server-jetty")
include(":ktor-server:ktor-server-jetty:ktor-server-jetty-test-http2")
include(":ktor-server:ktor-server-servlet")
include(":ktor-server:ktor-server-tomcat")
include(":ktor-server:ktor-server-netty")
include(":ktor-server:ktor-server-cio")
include(":ktor-client")
include(":ktor-client:ktor-client-core")
include(":ktor-client:ktor-client-tests")
include(":ktor-client:ktor-client-apache")
include(":ktor-client:ktor-client-android")
include(":ktor-client:ktor-client-cio")
if (native_targets_enabled) {
    include(":ktor-client:ktor-client-curl")
    include(":ktor-client:ktor-client-ios")
}
if (currentJdk >= 11) {
    include(":ktor-client:ktor-client-java")
}
include(":ktor-client:ktor-client-jetty")
include(":ktor-client:ktor-client-js")
include(":ktor-client:ktor-client-mock")
include(":ktor-client:ktor-client-okhttp")
include(":ktor-client:ktor-client-plugins")
include(":ktor-client:ktor-client-plugins:ktor-client-json")
include(":ktor-client:ktor-client-plugins:ktor-client-json:ktor-client-json-tests")
include(":ktor-client:ktor-client-plugins:ktor-client-json:ktor-client-gson")
include(":ktor-client:ktor-client-plugins:ktor-client-json:ktor-client-jackson")
include(":ktor-client:ktor-client-plugins:ktor-client-json:ktor-client-serialization")
include(":ktor-client:ktor-client-plugins:ktor-client-auth")
include(":ktor-client:ktor-client-plugins:ktor-client-logging")
include(":ktor-client:ktor-client-plugins:ktor-client-encoding")
include(":ktor-client:ktor-client-plugins:ktor-client-websockets")
include(":ktor-client:ktor-client-plugins:ktor-client-content-negotiation")
include(":ktor-client:ktor-client-plugins:ktor-client-content-negotiation:ktor-client-content-negotiation-tests")
include(":ktor-server:ktor-server-plugins:ktor-server-auth")
include(":ktor-server:ktor-server-plugins:ktor-server-auth-jwt")
include(":ktor-server:ktor-server-plugins:ktor-server-auth-ldap")
include(":ktor-server:ktor-server-plugins:ktor-server-auto-head-response")
include(":ktor-server:ktor-server-plugins:ktor-server-caching-headers")
include(":ktor-server:ktor-server-plugins:ktor-server-call-id")
include(":ktor-server:ktor-server-plugins:ktor-server-call-logging")
include(":ktor-server:ktor-server-plugins:ktor-server-compression")
include(":ktor-server:ktor-server-plugins:ktor-server-conditional-headers")
include(":ktor-server:ktor-server-plugins:ktor-server-content-negotiation")
include(":ktor-server:ktor-server-plugins:ktor-server-cors")
include(":ktor-server:ktor-server-plugins:ktor-server-data-conversion")
include(":ktor-server:ktor-server-plugins:ktor-server-default-headers")
include(":ktor-server:ktor-server-plugins:ktor-server-double-receive")
include(":ktor-server:ktor-server-plugins:ktor-server-forwarded-header")
include(":ktor-server:ktor-server-plugins:ktor-server-freemarker")
include(":ktor-server:ktor-server-plugins:ktor-server-hsts")
include(":ktor-server:ktor-server-plugins:ktor-server-html-builder")
include(":ktor-server:ktor-server-plugins:ktor-server-http-redirect")
include(":ktor-server:ktor-server-plugins:ktor-server-locations")
include(":ktor-server:ktor-server-plugins:ktor-server-metrics")
include(":ktor-server:ktor-server-plugins:ktor-server-metrics-micrometer")
include(":ktor-server:ktor-server-plugins:ktor-server-mustache")
include(":ktor-server:ktor-server-plugins:ktor-server-partial-content")
include(":ktor-server:ktor-server-plugins:ktor-server-pebble")
include(":ktor-server:ktor-server-plugins:ktor-server-sessions")
include(":ktor-server:ktor-server-plugins:ktor-server-status-pages")
include(":ktor-server:ktor-server-plugins:ktor-server-thymeleaf")
include(":ktor-server:ktor-server-plugins:ktor-server-velocity")
include(":ktor-server:ktor-server-plugins:ktor-server-webjars")
include(":ktor-server:ktor-server-plugins:ktor-server-websockets")
include(":ktor-server:ktor-server-plugins")
include(":ktor-http")
include(":ktor-http:ktor-http-cio")
include(":ktor-io")
include(":ktor-utils")
include(":ktor-network")
include(":ktor-network:ktor-network-tls")
include(":ktor-network:ktor-network-tls:ktor-network-tls-certificates")
include(":ktor-bom")
include(":ktor-test-dispatcher")
include(":ktor-shared")
include(":ktor-shared:ktor-shared-serialization")
include(":ktor-shared:ktor-shared-serialization-kotlinx")
include(":ktor-shared:ktor-shared-serialization-kotlinx:ktor-shared-serialization-kotlinx-tests")
include(":ktor-shared:ktor-shared-serialization-kotlinx:ktor-shared-serialization-kotlinx-json")
include(":ktor-shared:ktor-shared-serialization-kotlinx:ktor-shared-serialization-kotlinx-cbor")
include(":ktor-shared:ktor-shared-serialization-kotlinx:ktor-shared-serialization-kotlinx-xml")
include(":ktor-shared:ktor-shared-serialization-gson")
include(":ktor-shared:ktor-shared-serialization-jackson")
include(":ktor-shared:ktor-shared-events")
include(":ktor-legacy")
include(":ktor-legacy:ktor-client-legacy")
include(":ktor-legacy:ktor-server-legacy")
include(":ktor-legacy:ktor-auth")
include(":ktor-legacy:ktor-auth-jwt")
include(":ktor-legacy:ktor-auth-ldap")
include(":ktor-legacy:ktor-client-legacy")
include(":ktor-legacy:ktor-freemarker")
include(":ktor-legacy:ktor-html-builder")
include(":ktor-legacy:ktor-locations")
include(":ktor-legacy:ktor-metrics")
include(":ktor-legacy:ktor-metrics-micrometer")
include(":ktor-legacy:ktor-mustache")
include(":ktor-legacy:ktor-pebble")
include(":ktor-legacy:ktor-server-legacy")
include(":ktor-legacy:ktor-thymeleaf")
include(":ktor-legacy:ktor-velocity")
include(":ktor-legacy:ktor-webjars")
include(":ktor-legacy:ktor-websockets")