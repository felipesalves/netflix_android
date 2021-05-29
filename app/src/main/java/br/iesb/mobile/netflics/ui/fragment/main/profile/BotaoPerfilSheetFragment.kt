package br.iesb.mobile.netflics.ui.fragment.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.netflics.databinding.FragmentPerfilBotaoSheetBinding
import br.iesb.mobile.netflics.domain.AppResultado
import br.iesb.mobile.netflics.domain.Profile
import br.iesb.mobile.netflics.viewmodel.PerfilViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BotaoPerfilSheetFragment(
    private val profile: Profile?,
    private val callback: (profile: Profile?) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPerfilBotaoSheetBinding
    private val viewmodel: PerfilViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPerfilBotaoSheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewmodel = viewmodel

        viewmodel.currentProfile = MutableLiveData(profile)

        viewmodel.result.observe(viewLifecycleOwner) {
            when (it) {
                is AppResultado.Success -> callback(viewmodel.currentProfile?.value)
                is AppResultado.Error -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
            dismiss()
        }

        return binding.root
    }

    @Suppress("UNUSED_PARAMETER")
    fun save(v: View) {
        viewmodel.createOrUpdateProfile()
    }

    @Suppress("UNUSED_PARAMETER")
    fun cancel(v: View) {
        dismiss()
    }

}