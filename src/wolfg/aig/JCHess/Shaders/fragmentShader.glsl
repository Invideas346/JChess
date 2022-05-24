#version 330

in vec2 v_texCoords;
out vec4 fragColor;
uniform sampler2D u_textureSampler;

void main() {
    fragColor = texture(u_textureSampler, v_texCoords);
}