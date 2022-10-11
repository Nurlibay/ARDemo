package uz.unidev.ardemo

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.permissionx.guolindev.PermissionX
import uz.unidev.ardemo.databinding.ScreenPermissionBinding

/**
 *  Created by Nurlibay Koshkinbaev on 11/10/2022 12:15
 */

class PermissionScreen : Fragment(R.layout.screen_permission) {

    private lateinit var binding: ScreenPermissionBinding
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenPermissionBinding.bind(view).apply {
            btnPermission.setOnClickListener {
                PermissionX.init(requireActivity())
                    .permissions(Manifest.permission.CAMERA)
                    .request { allGranted, _, deniedList ->
                        if(allGranted){
                            navController.navigate(R.id.action_permissionScreen_to_ARScreen)
                        } else {
                            Toast.makeText(requireContext(), "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }

}