package mundroid.apps.basicbankingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mundroid.apps.basicbankingapp.R
import mundroid.apps.basicbankingapp.databinding.TicketCustomersBinding


class CustomerAdapter(private val dataSet: ArrayList<Customer>, private val context: Context) :
    RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding: TicketCustomersBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.ticket_customers, parent, false
        )
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val item = dataSet[position]
        with(holder) {
            with(item) {
                binding.customerName.text = item.name
//                Glide.with(context).load(item.customerImg).into(binding.customerImg)
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

    class CustomerViewHolder(val binding: TicketCustomersBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}