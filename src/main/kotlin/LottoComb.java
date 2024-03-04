import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class LottoComb {
	private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45};
	private static final int R = 6; //추출할 갯수 nCr에서 r을 의미
	private static int[] out;
	private static int count = 0;

	// 소수
	private static final int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43};

	// 합성수
	private static final int[] compositeNumbers = {1, 4, 8, 10, 14, 16, 20, 22, 25, 26, 28, 32, 34, 35, 38, 40, 44};

	// 쌍수
	private static final int[] twinNumbers = {11, 22, 33, 44};

	// 완전 제곱수
	private static final int[] squareNumbers = {1, 4, 9, 16, 25, 36};

	private static boolean checkLottoNumberValidation(int[] numbers) {
		if (numbers.length != 6) return false;

		int sum = 0;

		int oddCount = 0;
		int evenCount = 0;

		// 저 비율 - 1부터 22까지
		int lowCount = 0;
		// 고 비율 - 23부터 45까지
		int highCount = 0;

		//1부터 10까지
		int aGroupCount = 0;
		//11부터 20까지
		int bGroupCount = 0;
		//21부터 30까지
		int cGroupCount = 0;
		//31부터 40까지
		int dGroupCount = 0;
		//41부터 45까지
		int eGroupCount = 0;

		int endNumberSum = 0;

		//끝수 0의 갯수
		int endNumber0 = 0;
		//끝수 1의 갯수
		int endNumber1 = 0;
		//끝수 2의 갯수
		int endNumber2 = 0;
		//끝수 3의 갯수
		int endNumber3 = 0;
		//끝수 4의 갯수
		int endNumber4 = 0;
		//끝수 5의 갯수
		int endNumber5 = 0;
		//끝수 6의 갯수
		int endNumber6 = 0;
		//끝수 7의 갯수
		int endNumber7 = 0;
		//끝수 8의 갯수
		int endNumber8 = 0;
		//끝수 9의 갯수
		int endNumber9 = 0;

		int primeNumberCount = 0;

		int compositeNumberCount = 0;

		int multiple3NumberCount = 0;

		int multiple5NumberCount = 0;

		int twinNumberCount = 0;

		int squareNumberCount = 0;

		for (int number : numbers) {
			sum += number;

			if (number % 2 == 0) {
				evenCount++;
			} else {
				oddCount++;
			}

			if (number < 23) {
				lowCount++;
			} else {
				highCount++;
			}

			if (number >= 1 && number <= 10) {
				aGroupCount++;
			} else if (number > 10 && number <= 20) {
				bGroupCount++;
			} else if (number > 20 && number <= 30) {
				cGroupCount++;
			} else if (number > 30 && number <= 40) {
				dGroupCount++;
			} else if (number > 40 && number <= 45) {
				eGroupCount++;
			}

			int endNumber = number % 10;
			endNumberSum += endNumber;

			switch (endNumber) {
				case 0:
					endNumber0++;
					break;
				case 1:
					endNumber1++;
					break;
				case 2:
					endNumber2++;
					break;
				case 3:
					endNumber3++;
					break;
				case 4:
					endNumber4++;
					break;
				case 5:
					endNumber5++;
					break;
				case 6:
					endNumber6++;
					break;
				case 7:
					endNumber7++;
					break;
				case 8:
					endNumber8++;
					break;
				case 9:
					endNumber9++;
					break;
			}

			for (int primeNumber : primeNumbers) {
				if (number == primeNumber) {
					primeNumberCount++;
					break;
				}
			}

			for (int compositeNumber : compositeNumbers) {
				if (number == compositeNumber) {
					compositeNumberCount++;
					break;
				}
			}

			if (number % 3 == 0) multiple3NumberCount++;

			if (number % 5 == 0) multiple5NumberCount++;

			for (int twinNumber : twinNumbers) {
				if (number == twinNumber) {
					twinNumberCount++;
					break;
				}
			}

			for (int squareNumber : squareNumbers) {
				if (number == squareNumber) {
					squareNumberCount++;
					break;
				}
			}
		}

		//합계 체크 100 ~ 180
		if (sum < 100 || sum > 180) return false;

		//홀짝비율 6:0,0:6 제외
		if (evenCount == 6 || oddCount == 6) return false;

		//저고비율 6:0,0:6 제외
		if (lowCount == 6 || highCount == 6) return false;

		//동일 구간 3개까지
		if (aGroupCount > 3 || bGroupCount > 3 || cGroupCount > 3 || dGroupCount > 3 || eGroupCount > 3) return false;

		//끝수합 15 ~ 38
		if (endNumberSum < 15 || endNumberSum > 38) return false;

		//소수는 3개까지
		if (primeNumberCount > 3) return false;

		//합성수는 3개까지
		if (compositeNumberCount > 3) return false;

		//3의 배수는 3개까지
		if (multiple3NumberCount > 3) return false;

		//5의 배수는 2개까지
		if (multiple5NumberCount > 2) return false;

		//쌍수는 2개까지
		if (twinNumberCount > 2) return false;

		//시작번호가 14이상, 끝번호 30이하 제외
		if (numbers[0] > 14 || numbers[5] < 30) return false;

		//연속수는 2개까지
		if (checkSequenceNumbers(numbers,6) == false) return false;

		//이웃수는 3개까지
		if (!checkNeighborNumbers(numbers)) return false;

		//동일 끝수는 3개까지
		if (endNumber0 > 3 || endNumber1 > 3 || endNumber2 > 3 || endNumber3 > 3 || endNumber4 > 3 || endNumber5 > 3 || endNumber6 > 3 || endNumber7 > 3 || endNumber8 > 3 || endNumber9 > 3)
			return false;

		//완전제곱수는 2개까지
		if (squareNumberCount > 2) return false;

		//AC는 6~10까지
		int ac = getACValue(numbers);
		if (ac < 6 || ac > 10) return false;

		return true;
	}

	private static int getACValue(int[] numbers) {
		if (numbers.length != 6) return -1;

		int ac = 0;
		Set<Integer> setAC = new HashSet<>();
		int a1 = numbers[5] - numbers[4];
		int a2 = numbers[5] - numbers[3];
		int a3 = numbers[5] - numbers[2];
		int a4 = numbers[5] - numbers[1];
		int a5 = numbers[5] - numbers[0];

		int a6 = numbers[4] - numbers[3];
		int a7 = numbers[4] - numbers[2];
		int a8 = numbers[4] - numbers[1];
		int a9 = numbers[4] - numbers[0];

		int a10 = numbers[3] - numbers[2];
		int a11 = numbers[3] - numbers[1];
		int a12 = numbers[3] - numbers[0];

		int a13 = numbers[2] - numbers[1];
		int a14 = numbers[2] - numbers[0];

		int a15 = numbers[1] - numbers[0];

		setAC.add(a1);
		setAC.add(a2);
		setAC.add(a3);
		setAC.add(a4);
		setAC.add(a5);
		setAC.add(a6);
		setAC.add(a7);
		setAC.add(a8);
		setAC.add(a9);
		setAC.add(a10);
		setAC.add(a11);
		setAC.add(a12);
		setAC.add(a13);
		setAC.add(a14);
		setAC.add(a15);

		ac = setAC.size() - 5;

		return ac;
	}

	/**
	 * 이전회차 당첨번호들에 대한 이웃수 체크
	 * 3개 초과시 false
	 * @param numbers
	 * @return
	 */
	private static boolean checkNeighborNumbers(int[] numbers) {
		int[] targets = {7,19,26,37,39,44};

		/*int count = 0;

		for (int number : numbers) {
			for (int target : targets) {
				int neighborNumber1 = target - 1;
				int neighborNumber2 = target + 1;

				if (number == neighborNumber1 || number == neighborNumber2) {
					count ++;

					if (count > 3) return false;
				}
			}
		}*/

		return true;
	}

	/**
	 * 가로, 세로 라인 각각 적어도 2라인 이상의 멸라인(destruction line : 라인에 번호가 아무것도 선택이 안된 라인)이 있어야 한다.
	 * @param numbers
	 * @return
	 */
	private static boolean checkDestructionLine(int[] numbers) {
		// 가로 라인
		int[] horizontalGroup1 = {
				1,2,3,4,5,6,7
		};
		int[] horizontalGroup2 = {
				8,9,10,11,12,13,14
		};
		int[] horizontalGroup3 = {
				15,16,17,18,19,20,21
		};
		int[] horizontalGroup4 = {
				22,23,24,25,26,27,28
		};
		int[] horizontalGroup5 = {
				29,30,31,32,33,34,35
		};
		int[] horizontalGroup6 = {
				36,37,38,39,40,41,42
		};
		int[] horizontalGroup7 = {
				43,44,45
		};

		int horizontalGroupCount = 0;
		if (!checkArray4(numbers,horizontalGroup1)) horizontalGroupCount++;
		if (!checkArray4(numbers,horizontalGroup2)) horizontalGroupCount++;
		if (!checkArray4(numbers,horizontalGroup3)) horizontalGroupCount++;
		if (!checkArray4(numbers,horizontalGroup4)) horizontalGroupCount++;
		if (!checkArray4(numbers,horizontalGroup5)) horizontalGroupCount++;
		if (!checkArray4(numbers,horizontalGroup6)) horizontalGroupCount++;
		if (!checkArray4(numbers,horizontalGroup7)) horizontalGroupCount++;

		// 세로 라인
		int[] verticalGroup1 = {
				1,8,15,22,29,36,43
		};
		int[] verticalGroup2 = {
				2,9,16,23,30,37,44
		};
		int[] verticalGroup3 = {
				3,10,17,24,31,38,45
		};
		int[] verticalGroup4 = {
				4,11,18,25,32,39
		};
		int[] verticalGroup5 = {
				5,12,19,26,33,40
		};
		int[] verticalGroup6 = {
				6,13,20,27,34,41
		};
		int[] verticalGroup7 = {
				7,14,21,28,35,42
		};

		int verticalGroupCount = 0;
		if (!checkArray4(numbers,verticalGroup1)) verticalGroupCount++;
		if (!checkArray4(numbers,verticalGroup2)) verticalGroupCount++;
		if (!checkArray4(numbers,verticalGroup3)) verticalGroupCount++;
		if (!checkArray4(numbers,verticalGroup4)) verticalGroupCount++;
		if (!checkArray4(numbers,verticalGroup5)) verticalGroupCount++;
		if (!checkArray4(numbers,verticalGroup6)) verticalGroupCount++;
		if (!checkArray4(numbers,verticalGroup7)) verticalGroupCount++;

		if (horizontalGroupCount >= 2 && verticalGroupCount >= 2) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean checkLottoPatternValidation(int[] numbers) {
		// 멸라인 체크
		if (!checkDestructionLine(numbers)) return false;

		// 삼각패턴
		// 좌상 삼각 패턴
		int[] leftTopTriangles = {
				1,2,3,4,5,6,7,
				8,9,10,11,12,13,
				15,16,17,18,19,
				22,23,24,25,
				29,30,31,
				36,37,
				43
		};
		if (!checkArray1(numbers,leftTopTriangles)) return false;

		// 좌하 삼각 패턴
		int[] leftBottomTriangles = {
				1,
				8,9,
				15,16,17,
				22,23,24,25,
				29,30,31,32,33,
				36,37,38,39,40,41,
				43,44,45
		};
		if (!checkArray1(numbers,leftBottomTriangles)) return false;

		// 우상 삼각 패턴
		int[] rightTopTriangles = {
				1,2,3,4,5,6,7,
				9,10,11,12,13,14,
				17,18,19,20,21,
				25,26,27,28,
				33,34,35,
				41,42
		};
		if (!checkArray1(numbers,rightTopTriangles)) return false;

		// 우하 삼각 패턴
		int[] rightBottomTriangles = {
				7,
				13,14,
				19,20,21,
				25,26,27,28,
				31,32,33,34,35,
				37,38,39,40,41,42,
				43,44,45
		};
		if (!checkArray1(numbers,rightBottomTriangles)) return false;

		// 퐁당퐁당 패턴
		// 퐁당퐁당 세로라인1
		int[] pongdang1 = {
				1,2,4,5,
				8,9,11,12,
				15,16,18,19,
				22,23,25,26,
				29,30,32,33,
				36,37,39,40,
				43,44
		};
		if (!checkArray1(numbers,pongdang1)) return false;

		// 퐁당퐁당 세로라인2
		int[] pongdang2 = {
				3,4,6,7,
				10,11,13,14,
				17,18,20,21,
				24,25,27,28,
				31,32,34,35,
				38,39,41,42,
				45
		};
		if (!checkArray1(numbers,pongdang2)) return false;

		// 좌우 패턴
		int[] leftRight = {
				1,2,6,7,
				8,9,13,14,
				15,16,20,21,
				22,23,27,28,
				29,30,34,35,
				36,37,41,42,
				43,44
		};
		if (!checkArray1(numbers,leftRight)) return false;

		// 가로 연속 3줄 패턴
		// 가로라인 1,2,3
		int[] horizontalLine1 = {
				1,2,3,4,5,6,7,
				8,9,10,11,12,13,14,
				15,16,17,18,19,20,21
		};
		if (!checkArray1(numbers,horizontalLine1)) return false;

		// 가로라인 2,3,4
		int[] horizontalLine2 = {
				8,9,10,11,12,13,14,
				15,16,17,18,19,20,21,
				22,23,24,25,26,27,28
		};
		if (!checkArray1(numbers,horizontalLine2)) return false;

		// 가로라인 3,4,5
		int[] horizontalLine3 = {
				15,16,17,18,19,20,21,
				22,23,24,25,26,27,28,
				29,30,31,32,33,34,35
		};
		if (!checkArray1(numbers,horizontalLine3)) return false;

		// 가로라인 4,5,6
		int[] horizontalLine4 = {
				22,23,24,25,26,27,28,
				29,30,31,32,33,34,35,
				36,37,38,39,40,41,42
		};
		if (!checkArray1(numbers,horizontalLine4)) return false;

		// 가로라인 5,6,7
		int[] horizontalLine5 = {
				29,30,31,32,33,34,35,
				36,37,38,39,40,41,42,
				43,44,45
		};
		if (!checkArray1(numbers,horizontalLine5)) return false;

		// 가로연속 6줄 패턴
		int[] horizontalGroup1 = {
				1,2,3,4,5,6,7
		};
		int[] horizontalGroup2 = {
				8,9,10,11,12,13,14
		};
		int[] horizontalGroup3 = {
				15,16,17,18,19,20,21
		};
		int[] horizontalGroup4 = {
				22,23,24,25,26,27,28
		};
		int[] horizontalGroup5 = {
				29,30,31,32,33,34,35
		};
		int[] horizontalGroup6 = {
				36,37,38,39,40,41,42
		};
		int[] horizontalGroup7 = {
				43,44,45
		};

		// 가로 1라인부터 연속 6줄
		if (checkArray2(numbers,horizontalGroup1) == false
				&& checkArray2(numbers,horizontalGroup2) == false
				&& checkArray2(numbers,horizontalGroup3) == false
				&& checkArray2(numbers,horizontalGroup4) == false
				&& checkArray2(numbers,horizontalGroup5) == false
				&& checkArray2(numbers,horizontalGroup6) == false
		) {
			return false;
		}

		// 가로 2라인부터 연속 6줄
		if (checkArray2(numbers,horizontalGroup2) == false
				&& checkArray2(numbers,horizontalGroup3) == false
				&& checkArray2(numbers,horizontalGroup4) == false
				&& checkArray2(numbers,horizontalGroup5) == false
				&& checkArray2(numbers,horizontalGroup6) == false
				&& checkArray2(numbers,horizontalGroup7) == false
		) {
			return false;
		}

		// 가로 라인마다 선택한 번호가 3개를 초과하는 경우 제외
		if (checkArray3(numbers,horizontalGroup1) == false
				|| checkArray3(numbers,horizontalGroup2) == false
				|| checkArray3(numbers,horizontalGroup3) == false
				|| checkArray3(numbers,horizontalGroup4) == false
				|| checkArray3(numbers,horizontalGroup5) == false
				|| checkArray3(numbers,horizontalGroup6) == false
		) {
			return false;
		}

		// 세로 연속 3줄 패턴
		// 세로 라인 1,2,3
		int[] verticalLine1 = {
				1,2,3,
				8,9,10,
				15,16,17,
				22,23,24,
				29,30,31,
				36,37,38,
				43,44,45
		};
		if (!checkArray1(numbers,verticalLine1)) return false;

		// 세로 라인 2,3,4
		int[] verticalLine2 = {
				2,3,4,
				9,10,11,
				16,17,18,
				23,24,25,
				30,31,32,
				37,38,39,
				44,45
		};
		if (!checkArray1(numbers,verticalLine2)) return false;

		// 세로 라인 3,4,5
		int[] verticalLine3 = {
				3,4,5,
				10,11,12,
				17,18,19,
				24,25,26,
				31,32,33,
				38,39,40,
				45
		};
		if (!checkArray1(numbers,verticalLine3)) return false;

		// 세로 라인 4,5,6
		int[] verticalLine4 = {
				4,5,6,
				11,12,13,
				18,19,20,
				25,26,27,
				32,33,34,
				39,40,41
		};
		if (!checkArray1(numbers,verticalLine4)) return false;

		// 세로 라인 5,6,7
		int[] verticalLine5 = {
				5,6,7,
				12,13,14,
				19,20,21,
				26,27,28,
				33,34,35,
				40,41,42
		};
		if (!checkArray1(numbers,verticalLine5)) return false;

		// 세로연속 6줄 패턴

		int[] verticalGroup1 = {
				1,8,15,22,29,36,43
		};
		int[] verticalGroup2 = {
				2,9,16,23,30,37,44
		};
		int[] verticalGroup3 = {
				3,10,17,24,31,38,45
		};
		int[] verticalGroup4 = {
				4,11,18,25,32,39
		};
		int[] verticalGroup5 = {
				5,12,19,26,33,40
		};
		int[] verticalGroup6 = {
				6,13,20,27,34,41
		};
		int[] verticalGroup7 = {
				7,14,21,28,35,42
		};

		// 세로 1라인부터 연속 6줄
		if (checkArray2(numbers,verticalGroup1) == false
				&& checkArray2(numbers,verticalGroup2) == false
				&& checkArray2(numbers,verticalGroup3) == false
				&& checkArray2(numbers,verticalGroup4) == false
				&& checkArray2(numbers,verticalGroup5) == false
				&& checkArray2(numbers,verticalGroup6) == false
		) {
			return false;
		}

		// 세로 2라인부터 연속 6줄
		if (checkArray2(numbers,verticalGroup2) == false
				&& checkArray2(numbers,verticalGroup3) == false
				&& checkArray2(numbers,verticalGroup4) == false
				&& checkArray2(numbers,verticalGroup5) == false
				&& checkArray2(numbers,verticalGroup6) == false
				&& checkArray2(numbers,verticalGroup7) == false
		) {
			return false;
		}

		// 세로 라인마다 선택한 번호가 3개를 초과하는 경우 제외
		if (checkArray3(numbers,verticalGroup1) == false
				|| checkArray3(numbers,verticalGroup2) == false
				|| checkArray3(numbers,verticalGroup3) == false
				|| checkArray3(numbers,verticalGroup4) == false
				|| checkArray3(numbers,verticalGroup5) == false
				|| checkArray3(numbers,verticalGroup6) == false
				|| checkArray3(numbers,verticalGroup7) == false
		) {
			return false;
		}

		// 로또 생성번호들이 모서리 패턴내에 있는 번호가 적어도 2개는 있어야 한다.
		int[] edgeGroup = {
				1,2,6,7,
				8,9,13,14,
				29,30,34,35,
				36,37,41,42,
				43,44,45
		};
		int edgeCount = 0;
		for (int number : numbers) {
			for (int edgeNumber : edgeGroup) {
				if (number == edgeNumber) edgeCount++;
			}
		}
		if (edgeCount < 2) return false;

		return true;
	}

	/**
	 * 로또9단 분석기법에 소개된 30가지 패턴인데, 6개의 번호가 해당 패턴내에 있을 경우 1등 당첨이 되지 않을 확률이 99% 라고 함
	 * @param numbers
	 * @return
	 */
	public static boolean checkLotto9DanPattern30(int[] numbers) {
		int[] pattern01 = {
			2,6,9,
			11,13,17,20,
			22,24,25,27,28,29,
			31,33,34,36,39,
			41,42,43,44
		};
		if (!checkArray1(numbers,pattern01)) {
			System.out.println("로또 9단 pattern01 false");
			return false;
		}

		int[] pattern02 = {
			2,6,9,
			11,13,16,17,19,
			22,23,25,26,28,30,
			31,33,35,36,37,39,
			42
		};
		if (!checkArray1(numbers,pattern02)) {
			System.out.println("로또 9단 pattern02 false");
			return false;
		}

		int[] pattern03 = {
			1,2,
			11,13,17,19,
			22,23,26,29,
			31,34,37,38,
			41,43
		};
		if (!checkArray1(numbers,pattern03)) {
			System.out.println("로또 9단 pattern03 false");
			return false;
		}

		int[] pattern04 = {
			6,7,9,
			11,17,19,
			24,27,28,
			30,33,35,37,
			40,41
		};
		if (!checkArray1(numbers,pattern04)) {
			System.out.println("로또 9단 pattern04 false");
			return false;
		}

		int[] pattern05 = {
			1,2,3,4,5,6,7,8,
			16,19,
			22,23,24,25,26,27,28,29,30,
			32,33,34,39
		};
		if (!checkArray1(numbers,pattern05)) {
			System.out.println("로또 9단 pattern05 false");
			return false;
		}

		int[] pattern06 = {
			1,2,3,4,6,7,10,
			18,20,
			21,22,23,24,25,28,
			35,37,40,
			42,43,44,45
		};
		if (!checkArray1(numbers,pattern06)) {
			System.out.println("로또 9단 pattern06 false");
			return false;
		}

		int[] pattern07 = {
			1,2,4,5,6,7,8,9,
			11,12,15,16,20,
			21,22,24,28,
			32,36,40,
			44,45
		};
		if (!checkArray1(numbers,pattern07)) {
			System.out.println("로또 9단 pattern07 false");
			return false;
		}

		int[] pattern08 = {
			1,2,4,5,7,8,10,
			13,14,15,17,
			24,25,27,28,
			31,36,40,
			41,44,45
		};
		if (!checkArray1(numbers,pattern08)) {
			System.out.println("로또 9단 pattern08 false");
			return false;
		}

		int[] pattern09 = {
			1,2,4,6,7,9,
			11,13,14,15,16,18,
			23,25,26,27,30,
			32,34,36,37,39,40,
			43,44,45
		};
		if (!checkArray1(numbers,pattern09)) {
			System.out.println("로또 9단 pattern09 false");
			return false;
		}

		int[] pattern10 = {
			1,2,3,4,5,6,
			13,17,18,19,
			22,23,24,26,29,30,
			31,32,33,36,37,38,39,40,
			42,44
		};
		if (!checkArray1(numbers,pattern10)) {
			System.out.println("로또 9단 pattern10 false");
			return false;
		}

		int[] pattern11 = {
			1,3,6,8,
			11,16,17,18,19,20,
			21,23,24,25,26,28,
			31,33,34,35,37,38,40,
			41,42
		};
		if (!checkArray1(numbers,pattern11)) {
			System.out.println("로또 9단 pattern11 false");
			return false;
		}

		int[] pattern12 = {
			1,3,5,7,9,
			11,13,15,17,
			22,23,24,25,26,27,28,30,
			31,33,36,38,40,
			43,44,45
		};
		if (!checkArray1(numbers,pattern12)) {
			System.out.println("로또 9단 pattern12 false");
			return false;
		}

		int[] pattern13 = {
			2,4,9,10,
			12,13,16,17,20,
			21,23,24,26,27,30,
			31,32,34,35,36,37,38,
			41,42,44
		};
		if (!checkArray1(numbers,pattern13)) {
			System.out.println("로또 9단 pattern13 false");
			return false;
		}

		int[] pattern14 = {
			8,9,10,
			11,12,13,14,16,17,20,
			22,23,24,25,26,27,28,29,30,
			32,35,39,40,
			41,42,45
		};
		if (!checkArray1(numbers,pattern14)) {
			System.out.println("로또 9단 pattern14 false");
			return false;
		}

		int[] pattern15 = {
			1,3,5,6,7,8,9,
			12,15,16,17,19,
			22,23,26,29,
			31,33,35,36,37,38,39,
			43,45
		};
		if (!checkArray1(numbers,pattern15)) {
			System.out.println("로또 9단 pattern15 false");
			return false;
		}

		int[] pattern16 = {
			1,2,3,5,8,9,10,
			15,16,17,18,
			22,25,26,27,28,
			31,33,34,35,36,38,40,
			41,42
		};
		if (!checkArray1(numbers,pattern16)) {
			System.out.println("로또 9단 pattern16 false");
			return false;
		}

		int[] pattern17 = {
			1,7,9,10,
			11,15,16,19,
			21,22,24,25,26,27,30,
			32,34,36,37,38,40,
			41,43,44,45
		};
		if (!checkArray1(numbers,pattern17)) {
			System.out.println("로또 9단 pattern17 false");
			return false;
		}

		int[] pattern18 = {
			3,4,5,7,8,10,
			11,12,14,15,16,17,18,19,
			23,28,29,30,
			35,36,37,40,
			41,42,43
		};
		if (!checkArray1(numbers,pattern18)) {
			System.out.println("로또 9단 pattern18 false");
			return false;
		}

		int[] pattern19 = {
			2,3,4,5,6,8,9,10,
			12,15,16,18,19,
			21,24,27,30,
			33,36,37,39,40,
			42,43
		};
		if (!checkArray1(numbers,pattern19)) {
			System.out.println("로또 9단 pattern19 false");
			return false;
		}

		int[] pattern20 = {
			3,8,9,10,
			12,15,17,18,20,
			21,23,26,27,28,29,30,
			32,33,36,37,38,39,40,
			41
		};
		if (!checkArray1(numbers,pattern20)) {
			System.out.println("로또 9단 pattern20 false");
			return false;
		}

		int[] pattern21 = {
			1,2,3,5,6,7,8,9,10,
			12,13,14,15,16,17,19,
			21,30,
			33,36,37,39,
			41,44
		};
		if (!checkArray1(numbers,pattern21)) {
			System.out.println("로또 9단 pattern21 false");
			return false;
		}

		int[] pattern22 = {
			1,2,3,5,6,7,9,
			11,13,16,17,19,20,
			22,23,25,27,29,30,
			32,34,35,37,38,
			41
		};
		if (!checkArray1(numbers,pattern22)) {
			System.out.println("로또 9단 pattern22 false");
			return false;
		}

		int[] pattern23 = {
			2,3,6,8,9,10,
			12,13,14,15,16,19,20,
			21,22,23,24,25,26,27,28,
			33,
			42,43,45
		};
		if (!checkArray1(numbers,pattern23)) {
			System.out.println("로또 9단 pattern23 false");
			return false;
		}

		int[] pattern24 = {
			2,3,4,6,9,10,
			11,14,15,18,19,
			23,24,26,27,
			31,34,35,36,38,
			41,42,43,44
		};
		if (!checkArray1(numbers,pattern24)) {
			System.out.println("로또 9단 pattern24 false");
			return false;
		}

		int[] pattern25 = {
			1,2,3,4,5,7,
			12,13,15,18,20,
			21,22,28,29,30,
			31,36,37,38,40,
			43,44,45
		};
		if (!checkArray1(numbers,pattern25)) {
			System.out.println("로또 9단 pattern25 false");
			return false;
		}

		int[] pattern26 = {
			1,2,4,5,6,7,8,
			11,12,13,14,
			22,23,25,26,27,28,29,30,
			32,39,40,
			41,42
		};
		if (!checkArray1(numbers,pattern26)) {
			System.out.println("로또 9단 pattern26 false");
			return false;
		}

		int[] pattern27 = {
			2,4,5,6,8,10,
			12,13,14,16,19,
			22,24,25,28,30,
			31,32,35,38,
			41,42,45
		};
		if (!checkArray1(numbers,pattern27)) {
			System.out.println("로또 9단 pattern27 false");
			return false;
		}

		int[] pattern28 = {
			1,7,9,10,
			12,13,16,17,18,19,
			23,24,25,28,30,
			31,33,34,37,40,
			41,42
		};
		if (!checkArray1(numbers,pattern28)) {
			System.out.println("로또 9단 pattern28 false");
			return false;
		}

		int[] pattern29 = {
			1,2,9,10,
			11,12,13,14,20,
			23,24,26,27,30,
			34,36,38,39,40,
			41,42,44
		};
		if (!checkArray1(numbers,pattern29)) {
			System.out.println("로또 9단 pattern29 false");
			return false;
		}

		int[] pattern30 = {
			1,7,8,
			14,15,16,17,18,19,20,
			21,28,29,
			31,32,33,34,35,36,
			42,43
		};
		if (!checkArray1(numbers,pattern30)) {
			System.out.println("로또 9단 pattern30 false");
			return false;
		}

		return true;
	}

	private static boolean checkArray1(int[] numbers, int[] targets) {
		int count = 0;
		for (int number : numbers) {
			for (int target : targets) {
				if (number == target) count++;
				if (count >= 6) return false;
			}
		}
		return true;
	}

	private static boolean checkArray2(int[] numbers, int[] targets) {
		for (int number : numbers) {
			for (int target : targets) {
				if (number == target) return false;
			}
		}
		return true;
	}

	private static boolean checkArray3(int[] numbers, int[] targets) {
		int count = 0;
		for (int number : numbers) {
			for (int target : targets) {
				if (number == target) count++;
				if (count > 3) return false;
			}
		}
		return true;
	}

	private static boolean checkArray4(int[] numbers, int[] targets) {
		for (int number : numbers) {
			for (int target : targets) {
				if (number == target) return true;
			}
		}
		return false;
	}

	private static void comb(int start, int end, int index) {
		if (end == 0) {
			//로또 번호 유효성 검사
			int[] checkLottoNumbers = new int[6];

			for (int i = 0; i < start; i++) {
				checkLottoNumbers[i] = out[i];
			}

			if (!checkLottoNumberValidation(checkLottoNumbers)) return;

			if (!checkLottoPatternValidation(checkLottoNumbers)) return;

			if (!checkLotto9DanPattern30(checkLottoNumbers)) return;

			for (int i = 0; i < start; i++) {
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
		} else if (index == data.length) {
			return;
		} else {
			out[start] = data[index];
			comb(start + 1, end - 1, index + 1); //여기가 다름
			comb(start, end, index + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		out = new int[data.length];
		for(int i=0;i<data.length;i++)
		{
			out[i] = data[i];
		}

		count = 0;
		comb(0,R,0);
		System.out.println("조합 총 갯수 : " + new DecimalFormat("#,###").format(count));
	}

	/**
	 * 연속된 번호가 몇개인지 체크
	 * @param target 오름차순으로 된 번호 배열이어야 한다
	 * @param counterLength 최대 몇개의 연속된 숫자를 체크할것인지?
	 */
	private static boolean checkSequenceNumbers(int[] target, int counterLength) {
		int[] sequentialCounter = new int[counterLength];
		int count = 0;

		for (int i = 0, len = target.length; i < len; i++) {
			int subCount = 0;

			for (int j = 1; j < len; j++) {
				if (target[j] == target[i] + 1) {
					subCount = subCount + 1;
				} else {
					continue;
				}
			}

			count = count + subCount;
			if (subCount == 0) {
				sequentialCounter[count] = sequentialCounter[count] + 1;
				count = 0;
			}
		}

		/*
		System.out.println("2연속 번호가 " + sequentialCounter[1] + "개");
		System.out.println("3연속 번호가 " + sequentialCounter[2] + "개");
		System.out.println("4연속 번호가 " + sequentialCounter[3] + "개");
		System.out.println("5연속 번호가 " + sequentialCounter[4] + "개");
		System.out.println("6연속 번호가 " + sequentialCounter[5] + "개");
		*/

		if (
				(sequentialCounter[1] > 2) || (sequentialCounter[2] > 1) || (sequentialCounter[3] > 0) || (sequentialCounter[4] > 0) || (sequentialCounter[5] > 0)
		) {
			return false;
		}

		return true;
	}
}