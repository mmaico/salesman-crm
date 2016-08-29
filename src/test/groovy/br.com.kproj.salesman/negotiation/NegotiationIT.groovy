package br.com.kproj.salesman.negotiation

import spock.lang.Specification


class NegotiationIT extends Specification {


    def "proposta de negociacao"() {

        expect:
            x + y == sum
        where:
            x | y | sum
            1 | 2 | 3
            2 | 5 | 7
            1 | 8 | 9
    }
}
