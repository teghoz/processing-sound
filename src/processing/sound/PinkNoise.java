/*
  Processing Sound (c) 2013-2015 Wilm Thoben
  Part of the Processing project - http://processing.org

  Copyright (c) 2011-12 Ben Fry and Casey Reas

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
*/

package processing.sound;
import processing.core.PApplet;

public class PinkNoise implements Noise{
	
	PApplet parent;
	private Engine m_engine;
	private int[] m_nodeId = {-1,-1};
	private float m_amp=0.5f;
	private float m_add=0;
	private float m_pos=0;
	
	public PinkNoise(PApplet theParent) {
		this.parent = theParent;
		parent.registerMethod("dispose", this);
		m_engine.setPreferences(theParent, 512, 44100);
    	m_engine.start();			
	}
	
	public void play(){
		m_nodeId = m_engine.pinkNoisePlay(m_amp, m_add, m_pos);
	}
	
	public void play(float amp, float add, float pos){
		m_amp=amp; m_add=add; m_pos=pos;
			this.play();
	}

	public void play(float amp, float add){
		m_amp=amp; m_add=add;
		this.play();
	}
	
	public void play(float amp){
		m_amp=amp;
		this.play();
	}
	
	private void set(){
		if(m_nodeId[0] != -1 ) {
			m_engine.pinkNoiseSet(m_amp, m_add, m_pos, m_nodeId);
		}
	}
		
	public void set(float amp, float add, float pos){
		m_amp=amp;
		this.set();
	}
	
	public void amp(float amp){
		m_amp=amp;
		this.set();
	}
	
	public void add(float add){
		m_add=add;
		this.set();
	}
	
	public void pan(float pos){
		m_pos=pos;
		this.set();
	}
/*
	public void out(int out){
		m_engine.out(out, m_nodeId);
	}
*/	
	public void stop(){
		m_engine.synthStop(m_nodeId);
	}
	
	public int[] returnId(){
		return m_nodeId;
	}

	public void dispose() {
		m_engine.synthStop(m_nodeId);
	}
};
	