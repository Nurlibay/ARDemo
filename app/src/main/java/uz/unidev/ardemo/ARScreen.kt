package uz.unidev.ardemo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import uz.unidev.ardemo.databinding.ScreenArBinding

/**
 *  Created by Nurlibay Koshkinbaev on 11/10/2022 12:15
 */

class ARScreen : Fragment(R.layout.screen_ar) {

    private lateinit var binding: ScreenArBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenArBinding.bind(view).apply {
            val arFragment = childFragmentManager.findFragmentById(R.id.fragment_ar) as ArFragment
            arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
                val anchor: Anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)
                arFragment.arSceneView.scene.addChild(anchorNode)

                val node = TransformableNode(arFragment.transformationSystem)
                node.setParent(anchorNode)

                ModelRenderable.builder().setSource(requireContext(), R.raw.notebook).build().thenAccept {
                    node.renderable = it
                    node.select()
                }
                node.scaleController.maxScale
                node.scaleController.minScale = 0.05f
            }
        }
    }

}