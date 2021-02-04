package com.example.biyuyu

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.biyuyu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    //Enable options menu in this fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    //Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //Handle item clicks of menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Get item id to handle item clicks
        val id = item!!.itemId
        //Handle item clicks
        if (id == R.id.user_info) {
            //Action here
            Toast.makeText(context, "Información de usuario", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.logout) {
            Toast.makeText(context, "Ha cerrado sesión", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}