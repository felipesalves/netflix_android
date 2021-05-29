package br.iesb.mobile.netflics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.netflics.R
import br.iesb.mobile.netflics.databinding.FragmentItemFilmeBinding
import br.iesb.mobile.netflics.domain.Filme
import com.squareup.picasso.Picasso

class FilmesAdapter(
    private val filmes: List<Filme>,
    private val filmeItem: ((Filme) -> Unit)
) : RecyclerView.Adapter<FilmesAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_item_filme,
            parent,
            false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.filmes = filmes[position]
        binding?.executePendingBindings()

        if (binding != null) {
            Picasso.get().load(binding?.filmes?.foto).into(binding.poster)
        }
    }

    override fun getItemCount() = filmes.size

   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding: FragmentItemFilmeBinding? = FragmentItemFilmeBinding.bind(view)
    }

}