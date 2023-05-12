package event;

import Charactor.*;

public class Event {
	// private static boolean hitBarrier = true;

	// public static void setHitBarrier(boolean hit) {
    //     hitBarrier = hit;
    //     if (hit) {
    //         lastBarrierHitTime = System.currentTimeMillis();
    //     }
    // }


			public static boolean checkHit(Player player,Wave wave,int playerSize,int waveHeight){
							if(player.x+playerSize>wave.x&&player.x<wave.x) {
								if(player.y+playerSize>=wave.y-waveHeight) {
									return true;
								}
							}
							return false;
			}

			public static boolean checkHitCoconut(Player player,Coconut coconut,int playerSize,int coconutHeight){
				if(player.x+playerSize>coconut.x&&player.x<coconut.x) {
					if(player.y+playerSize>=coconut.y-coconutHeight) {
						return true;
					}
				}
				return false;
		}

			public static boolean checkHitHeart(Player player,Heart heart,int playerSize,int heartHeight){
				if(player.x+playerSize>heart.x&&player.x<heart.x) {
					if(player.y+playerSize>=heart.y-heartHeight) {
						return true;
					}
				}
				return false;
			}

			public static boolean checkHitBarrier(Player player,Barrier barrier,int playerSize,int barrierHeight){
					if (player.x + playerSize > barrier.x && player.x < barrier.x) {
						if (player.y + playerSize >= barrier.y - barrierHeight) {
							return true;
						}
					}
				return false;
			}

			public static boolean checkHitGhost(Player player,Ghost ghost,int playerSize,int waveHeight){
				if(Math.abs(player.x-ghost.x) <= 10) {
					if((Math.abs(player.y-ghost.y) <=10)) {
						return true;
					}
				}
				return false;
			}
			
			public static void gameStop(Wave[] wave,Environment[] env) {

			}

}
