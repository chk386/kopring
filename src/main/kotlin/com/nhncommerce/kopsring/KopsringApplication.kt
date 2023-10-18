package com.nhncommerce.kopsring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@SpringBootApplication
class KopsringApplication

fun main(args: Array<String>) {
    runApplication<KopsringApplication>(*args) {
        addInitializers(
            beans {
                bean(name = "newBean2") {
                    "newBean2"
                }

                bean {
                    coRouter {
                        GET("/members") {
                            val aa = ref<String>("newBean2")

                            ServerResponse.ok()
                                .bodyValueAndAwait(aa)
                        }
                    }
                }
            }
        )
    }
}

@Configuration
class SampleBean {
    @Bean
    fun newBean(): String {
        val txt = "oldBean333333"
        println(txt)

        return txt
    }
}
