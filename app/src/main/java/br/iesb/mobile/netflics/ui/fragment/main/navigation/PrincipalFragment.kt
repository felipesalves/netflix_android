package br.iesb.mobile.netflics.ui.fragment.main.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import br.iesb.mobile.netflics.databinding.FragmentPrincipalBinding
import br.iesb.mobile.netflics.ui.adapter.FilmesAdapter
import br.iesb.mobile.netflics.viewmodel.FilmeListaViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class PrincipalFragment : Fragment() {

    private lateinit var binding: FragmentPrincipalBinding
    private val viewModel: FilmeListaViewModel by lazy {
        ViewModelProvider(this).get(FilmeListaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleViewFilme()
    }

    @SuppressLint("WrongConstant")
    private fun recycleViewFilme(){
        val recycleListView = binding.recyclerView
        recycleListView.layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)

        viewModel.filmeLista.observe(viewLifecycleOwner) { list ->
            recycleListView.adapter = FilmesAdapter(list) {

            }
        }

        viewModel.filmeList()
    }

}