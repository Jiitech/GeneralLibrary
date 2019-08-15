package com.wukj.uilibrary.model.favour.leonids.initializers;


import com.wukj.uilibrary.model.favour.leonids.Particle;

import java.util.Random;


public interface ParticleInitializer {

	void initParticle(Particle p, Random r);

}
