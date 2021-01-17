import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return the list of books"

    request {
        url "/"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
            [
                id: 1,
                title: "Cien años de soledad",
                author: "Gabriel García Márquez"
            ]
        )
    }
}