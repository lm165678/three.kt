package info.laht.threekt.renderers.shaders

import info.laht.threekt.core.Uniform
import info.laht.threekt.math.Color
import info.laht.threekt.math.Matrix3

object ShaderLib {

    val basic = Shader(
            mergeUniforms(
                    listOf(
                            UniformsLib.common,
                            UniformsLib.specularmap,
                            UniformsLib.envmap,
                            UniformsLib.aomap,
                            UniformsLib.lightmap,
                            UniformsLib.fog
                    )
            ),
            ShaderChunk.meshbasic_vert,
            ShaderChunk.meshbasic_frag
    )

    val lambert = Shader(
            mergeUniforms(
                    listOf(
                            UniformsLib.common,
                            UniformsLib.specularmap,
                            UniformsLib.envmap,
                            UniformsLib.aomap,
                            UniformsLib.lightmap,
                            UniformsLib.emissivemap,
                            UniformsLib.fog,
                            UniformsLib.lights
                    )
            ),
            ShaderChunk.meshlambert_vert,
            ShaderChunk.meshlambert_frag
    )

    val standard = Shader(

            mergeUniforms(listOf(
                    UniformsLib.common,
                    UniformsLib.envmap,
                    UniformsLib.aomap,
                    UniformsLib.lightmap,
                    UniformsLib.emissivemap,
                    UniformsLib.bumpmap,
                    UniformsLib.normalmap,
                    UniformsLib.displacementmap,
                    UniformsLib.roughnessmap,
                    UniformsLib.metalnessmap,
                    UniformsLib.fog,
                    UniformsLib.lights,
                    mapOf(
                            "emissive" to Uniform(Color(0x000000)),
                            "roughness" to Uniform(0.5f),
                            "metalness" to Uniform(0.5f),
                            "envMapIntensity" to Uniform(1f) // temporary
                    )
            )),

            ShaderChunk.meshphysical_vert,
            ShaderChunk.meshphysical_frag

    )

    val matcap = Shader(
            mergeUniforms(
                    listOf(
                            UniformsLib.common,
                            UniformsLib.bumpmap,
                            UniformsLib.normalmap,
                            UniformsLib.displacementmap,
                            UniformsLib.fog,
                            mutableMapOf(
                                    "matcap" to Uniform(null)
                            )
                    )
            ),
            ShaderChunk.meshmatcap_vert,
            ShaderChunk.meshmatcap_frag
    )

    val points = Shader(
            mergeUniforms(
                    listOf(
                            UniformsLib.points,
                            UniformsLib.fog
                    )
            ),
            ShaderChunk.points_vert,
            ShaderChunk.points_frag
    )

    val dashed = Shader(
            mergeUniforms(
                    listOf(
                            UniformsLib.common,
                            UniformsLib.fog,
                            mutableMapOf(
                                    "scale" to Uniform(1f),
                                    "dashSize" to Uniform(1f),
                                    "totalSize" to Uniform(2f)
                            )
                    )
            ),
            ShaderChunk.linedashed_vert,
            ShaderChunk.linedashed_frag
    )

    val depth = Shader(
            mergeUniforms(
                    listOf(
                            UniformsLib.common,
                            UniformsLib.displacementmap
                    )
            ),
            ShaderChunk.depth_vert,
            ShaderChunk.depth_frag
    )

    val normal = Shader(
            mergeUniforms(
                    listOf(
                            UniformsLib.common,
                            UniformsLib.bumpmap,
                            UniformsLib.normalmap,
                            UniformsLib.displacementmap,
                            mutableMapOf(
                                    "opacity" to Uniform(1f)
                            )
                    )
            ),
            ShaderChunk.normal_vert,
            ShaderChunk.normal_frag
    )

    val sprite = Shader(
            mergeUniforms(
                    listOf(
                            UniformsLib.sprite,
                            UniformsLib.fog
                    )
            ),
            ShaderChunk.sprite_vert,
            ShaderChunk.sprite_frag
    )

    val background = Shader(
            mutableMapOf(
                    "uvTransform" to Uniform(Matrix3()),
                    "t2D" to Uniform(null)
            ),
            ShaderChunk.background_vert,
            ShaderChunk.background_frag
    )

    val cube = Shader(
            mutableMapOf(
                    "tCube" to Uniform(null),
                    "tFlip" to Uniform(-1),
                    "opacity" to Uniform(1f)
            ),
            ShaderChunk.cube_vert,
            ShaderChunk.cube_frag
    )

    val physical = Shader(
            mergeUniforms(
                    listOf(
                            ShaderLib.standard.uniforms,
                            UniformsLib.bumpmap,
                            UniformsLib.normalmap,
                            UniformsLib.displacementmap,
                            mutableMapOf(
                                    "clearCoat" to Uniform(0f),
                                    "clearCoatRoughness" to Uniform(0f)
                            )
                    )
            ),
            ShaderChunk.normal_vert,
            ShaderChunk.normal_frag
    )


    operator fun get(name: String): Shader {
        return ShaderLib::class.java.getDeclaredField(name).get(this) as Shader
    }

    class Shader(
            val name: String,
            val uniforms: MutableMap<String, Uniform>,
            val vertexShader: String,
            val fragmentShader: String
    ) {

        constructor (uniforms: MutableMap<String, Uniform>,
                     vertexShader: String,
                     fragmentShader: String) : this("", uniforms, vertexShader, fragmentShader)

    }

}
