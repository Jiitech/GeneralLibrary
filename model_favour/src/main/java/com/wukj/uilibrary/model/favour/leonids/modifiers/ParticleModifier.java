package com.wukj.uilibrary.model.favour.leonids.modifiers;


import com.wukj.uilibrary.model.favour.leonids.Particle;

public interface ParticleModifier {

	/**
	 * modifies the specific value of a particle given the current miliseconds
	 * @param particle
	 * @param miliseconds
	 */
	void apply(Particle particle, long miliseconds);

}
