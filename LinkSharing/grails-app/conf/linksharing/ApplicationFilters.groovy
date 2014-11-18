package linksharing

class ApplicationFilters {

    def filters = {
        all(controller:"login|assets", action:'*', invert:"true") {
            before = {
                println params

                if(session["user"]){
                    return true
                }
                else {
                    redirect (controller: "login",action: "index",flash:[message:"Session expired.."])
                    return false
                }

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }

//        assets(controller: 'assets', action: '*'){
//
//        }
    }
}
