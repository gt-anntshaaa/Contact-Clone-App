package com.example.simplecontact.ui.main.detail

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simplecontact.R
import com.example.simplecontact.databinding.FragmentDetailBinding
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
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
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

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    private val args: DetailFragmentArgs by navArgs()
    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory((requireActivity().application as ContactApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phone = args.contactArgs.phoneNumber
        val id = args.contactArgs.id

        setupData()
        setHasOptionsMenu(true)
        listener.CALL(phone)
        listener.MESSAGE(phone)
        listener.DELETE(id)
    }



    private val listener = object:
        SystemListener {
        override fun CALL(phone: String) {
            binding.imgBtnCall.setOnClickListener {
                val callIntent: Intent = Uri.parse("tel:$phone").let { number ->
                    Intent(Intent.ACTION_DIAL, number)
                }
                startActivity(callIntent)
            }
        }

        override fun MESSAGE(phone: String) {
            binding.imgBtnMessage.setOnClickListener {
                val smsIntent: Intent = Uri.parse("smsto:$phone").let { number ->
                    Intent(Intent.ACTION_VIEW, number)
                }
                startActivity(smsIntent)
            }
        }

        override fun DELETE(contactId: Int) {
            binding.btnDelete.setOnClickListener {
                buildAlert(contactId)
            }
        }

        override fun UPDATE(contact: ContactEntity) {
            contactViewModel.update(contact)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fr_detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.editMenu -> {
                val action = DetailFragmentDirections.actionGlobalAddFragment()
                findNavController().navigate(action)
                ((activity as AppCompatActivity).supportActionBar?.setTitle("Edit Contact"))
                true
            }
            else -> throw Exception("Unknown item id")
        }
    }


    private fun buildAlert(contactId: Int){
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Kontak yang dipilih akan dihapus secara permanen dan tidak bisa di restore kembali.")
        builder.setPositiveButton("HAPUS", DialogInterface.OnClickListener { dialog, which ->
            contactViewModel.delete(contactId)
            val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
            findNavController().navigate(action)
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })

        val alertDialog = builder.create().show()
    }
    private fun setupData(){
        binding.tvName.text = args.contactArgs.firstname.plus(" ${args.contactArgs.lastname}")
        binding.tvPhone.text = args.contactArgs.phoneNumber
        binding.tvEmail.text = args.contactArgs.email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}