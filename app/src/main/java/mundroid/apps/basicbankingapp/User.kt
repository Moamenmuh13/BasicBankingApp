package mundroid.apps.basicbankingapp

class User(
    val id: Int,
    val name: String,
    val email: String,
    val currentBalance: Int,
    val personalID: Long,
    val cvv: Int,
    val cardNum: Long,
    val cardType: String
) {
}