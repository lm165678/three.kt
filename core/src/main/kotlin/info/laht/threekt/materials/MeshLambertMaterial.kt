package info.laht.threekt.materials

import info.laht.threekt.math.Color

class MeshLambertMaterial : Material(), MaterialWithColor, MaterialWithSkinning, MaterialWithMorphTarget, MaterialWithMorphNormals, MaterialWithEmissive, MaterialWithWireframe {

    override val color = Color( 0xffffff )

    override val emissive = Color( 0x000000 );
    override var emissiveIntensity = 1f;

    override var wireframe = false;
    override var wireframeLinewidth = 1f;
    var wireframeLinecap = "round";
    var wireframeLinejoin = "round";
    
    override var skinning = false
    override var morphTargets = false
    override var morphNormals = false

}