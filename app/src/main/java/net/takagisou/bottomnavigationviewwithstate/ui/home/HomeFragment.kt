package net.takagisou.bottomnavigationviewwithstate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.takagisou.bottomnavigationviewwithstate.databinding.FragmentHomeBinding
import net.takagisou.bottomnavigationviewwithstate.databinding.ItemHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val items = mutableListOf<String>()
        (0..30).forEach {
            items.add("${it}番目のアイテム")
        }
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter = HomeAdapter(items.toTypedArray())
        binding.recyclerview.layoutManager = LinearLayoutManager(root.context)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class HomeAdapter(private val items: Array<String>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class  ViewHolder(private val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun setup(text: String) {
            binding.textview.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = items[position]
        holder.setup(text)
    }

    override fun getItemCount(): Int = items.size
}