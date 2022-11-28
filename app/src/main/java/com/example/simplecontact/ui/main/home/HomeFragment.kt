package com.example.simplecontact.ui.main.home

import android.os.Bundle
import android.view.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.simplecontact.R
import com.example.simplecontact.databinding.FragmentHomeBinding
import com.example.simplecontact.ui.data.local.ContactApplication
import com.example.simplecontact.ui.main.adapter.AdapterContact
import com.example.simplecontact.ui.main.viewmodel.ContactViewModel
import com.example.simplecontact.ui.main.viewmodel.ContactViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    private lateinit var adapterContact: AdapterContact

    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory((requireActivity().application as ContactApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        adapterContact = AdapterContact(contactViewModel)
        binding.rvContact.adapter = adapterContact

        contactViewModel.allContact.observe(viewLifecycleOwner, Observer {
            it?.let { adapterContact.submitList(it) }
        })

        // search contact
        binding.etSearch.doOnTextChanged { text, start, before, count ->
            val searchQuery = "%$text"
            contactViewModel.search(searchQuery).observe(
                viewLifecycleOwner, Observer {
                    it?.let { adapterContact.submitList(it) }
                }
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fr_home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addMenu -> {
                val action = HomeFragmentDirections.actionGlobalAddFragment()
                findNavController().navigate(action)
            }
        }
        return  super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}