package com.example.crazyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.crazyapp.MainActivity
import com.example.crazyapp.R
import com.example.crazyapp.adapter.NoteAdapter
import com.example.crazyapp.databinding.FragmentHomeBinding
import com.example.crazyapp.model.Note
import com.example.crazyapp.viewmodel.NoteViewModel



class HomeFragment : Fragment(R.layout.fragment_home) , SearchView.OnQueryTextListener, MenuProvider{

    private lateinit var binding:FragmentHomeBinding
    private lateinit var notesViewModel:NoteViewModel
    private lateinit var noteAdapter: NoteAdapter



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding=FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root

    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val menuHost:MenuHost=requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner,Lifecycle.State.RESUMED)

        notesViewModel=(activity as MainActivity).noteViewModel

        binding.addNoteFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
        }
    }

    private fun updateUI(note:List<Note>?){
        if(note!=null){
            if(note.isEmpty()){
                binding.emptyNotesImage.visibility=View.GONE
                binding.homeRecyclerView.visibility=View.VISIBLE
            }else{
                binding.emptyNotesImage.visibility=View.VISIBLE
                binding.homeRecyclerView.visibility=View.GONE
            }
        }
    }

    private fun setUpHomeRecyclerView(){
        noteAdapter= NoteAdapter()
        binding.homeRecyclerView.apply{
            layoutManager=StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter=noteAdapter
        }

        activity?.let{
            notesViewModel.getAllNotes().observe(viewLifecycleOwner){
                note->noteAdapter.differ.submitList(note)
                updateUI(note)
            }
        }


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu,menu)

        val menuSearch=menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled=false

        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        //return
    }


}
