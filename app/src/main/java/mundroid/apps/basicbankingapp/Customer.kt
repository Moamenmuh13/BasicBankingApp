package mundroid.apps.basicbankingapp

data class Customer(
    var id: Int?,
    var name: String?,
    var email: String?,
    var currentBalance: String?,
    var personalID: String?,
    var cvv: String?,
    var cardNum: String?,
    var cardType: String?,
    var customerImg: Int? = null
) {

//    constructor(customerImg: Int?) : this(
//        customerImg,
//        null,
//        null,
//        null,
//        null,
//        null,
//        null,
//        null,
//        null,
//    ) {
//        this.customerImg = customerImg
//    }

}