package mundroid.apps.basicbankingapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mundroid.apps.basicbankingapp.databinding.ActivityInsertingDataBinding

class InsertingDataActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityInsertingDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_inserting_data)
        initViews()
    }

    private fun initViews() {
        binding.insertingBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.insertingBtn -> {
                insertingData()
            }
        }
    }

    private fun insertingData() {
        val name = binding.name.text.toString()
        val email = binding.email.text.toString()
        val currentBalance = binding.balance.text.toString() + "$"
        val personalID = binding.personalID.text.toString()
        val cvv = binding.cvv.text.toString() + " "
        val cardNum = binding.cardNum.text.toString() + " "
        val cardType = binding.cardType.text.toString()

        val dataBaseHandler = DatabaseHandler(this)

        if (name.isNotEmpty() && email.isNotEmpty() && currentBalance.isNotEmpty()
            && personalID.isNotEmpty() && cvv.isNotEmpty()
            && cardNum.isNotEmpty() && cardType.isNotEmpty()
        ) {
            val status = dataBaseHandler.addUser(
                Customer(
                    0,
                    name,
                    email,
                    currentBalance,
                    personalID,
                    cvv,
                    cardNum,
                    cardType
                )
            )
            if (status > -1) {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                binding.name.text.clear()
                binding.email.text.clear()
                binding.balance.text.clear()
                binding.personalID.text.clear()
                binding.cvv.text.clear()
                binding.cardNum.text.clear()
            } else {
                Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show()
            }

        }
    }

}

