package com.mithun.simplebible.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mithun.simplebible.databinding.DialogReaderSettingsBinding
import com.mithun.simplebible.utilities.Prefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReaderSettingsDialog : BottomSheetDialogFragment() {

    @Inject
    lateinit var prefs: Prefs

    private var _binding: DialogReaderSettingsBinding? = null
    private val binding get() = _binding!!

    private var onSettingsChanged: (() -> Unit)? = null

    companion object {
        fun newInstance(onSettingsChanged: () -> Unit): ReaderSettingsDialog {
            return ReaderSettingsDialog().apply {
                this.onSettingsChanged = onSettingsChanged
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogReaderSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()

        binding.btnSizeIncrease.setOnClickListener {
            prefs.readerFontSize += 1f
            updateUi()
            onSettingsChanged?.invoke()
        }

        binding.btnSizeDecrease.setOnClickListener {
            if (prefs.readerFontSize > 8f) {
                prefs.readerFontSize -= 1f
                updateUi()
                onSettingsChanged?.invoke()
            }
        }

        binding.btnSpacingIncrease.setOnClickListener {
            prefs.readerLineSpacing += 0.1f
            updateUi()
            onSettingsChanged?.invoke()
        }

        binding.btnSpacingDecrease.setOnClickListener {
            if (prefs.readerLineSpacing > 0.8f) {
                prefs.readerLineSpacing -= 0.1f
                updateUi()
                onSettingsChanged?.invoke()
            }
        }
    }

    private fun updateUi() {
        binding.tvFontSize.text = "${prefs.readerFontSize.toInt()}sp"
        binding.tvLineSpacing.text = String.format("%.1fx", prefs.readerLineSpacing)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
