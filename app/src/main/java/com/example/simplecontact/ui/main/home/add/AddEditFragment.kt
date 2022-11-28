package com.example.simplecontact.ui.main.home.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simplecontact.R
import com.example.simplecontact.databinding.FragmentAddEditBinding
import com.example.simplecontact.ui.data.local.ContactApplication
import com.example.simplecontact.ui.data.local.entity.ContactEntity
import com.example.simplecontact.ui.main.viewmodel.ContactViewModel
import com.example.simplecontact.ui.main.viewmodel.ContactViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
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

    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditBinding.inflate(inflater,container,false)
        return binding.root
    }

    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory((requireActivity().application as ContactApplication).repository)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fr_add_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.simpanMenu -> {
                val firstname = binding.etFirstname.text.toString()
                val lastname = binding.etLastname.text.toString()
                val phone = binding.etPhone.text.toString()
                val email = binding.etEmail.text.toString()
                if (isSucces()) {
                    contactViewModel.insert(ContactEntity(firstname,lastname,email,phone))
                    Toast.makeText(requireContext(), "saving succes", Toast.LENGTH_SHORT).show()
                    clear()
                }else{
                    Toast.makeText(requireContext(), "failed to save", Toast.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isSucces() : Boolean{
        return !TextUtils.isEmpty(binding.etFirstname.text) || !TextUtils.isEmpty(binding.etLastname.text)
    }

    private fun clear(){
        binding.etFirstname.text.clear()
        binding.etLastname.text.clear()
        binding.etPhone.text.clear()
        binding.etEmail.text.clear()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}