package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_top, container, false)
        val imgButtonInfo : ImageButton = view.findViewById(R.id.imgInfo)

        val imgMenu: ImageButton = view.findViewById(R.id.imgMenu)

        imgButtonInfo.setOnClickListener {
            Toast.makeText(requireContext(), "En desarrollo", Toast.LENGTH_SHORT).show()
        }

        imgMenu.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), imgMenu)
            popupMenu.menuInflater.inflate(R.menu.menu_options, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.option1 -> {
                        true
                    }
                    R.id.option2 -> {
                        val intent = Intent(requireContext(), FichajeAlumnoActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.option3 -> {
                        val intent = Intent(requireContext(), ResumenAsistenciaActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.option4 -> {
                        val intent = Intent(requireContext(), PreferencesActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.option5 -> {
                        val intent = Intent(requireContext(), PerfilActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.option6 -> {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TopFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TopFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}