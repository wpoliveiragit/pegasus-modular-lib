package br.com.pegasus.module.toolkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HackerHankSantander {

	public static void main(String[] args) throws Exception {
		List<Ar> arList = Arrays.asList(//
				createAr(2, "1 2 1 2 1 3 2"), //
				createAr(3, "10 20 20 10 10 30 50 10 20"), //
				createAr(6, "1 2 1 2 1 3 2 3 6 6 7 7 7 7")//
		);

		arList.forEach(ar -> System.out.printf(//
				"result: %d\nexpec: %d\n\n", sockMerchant(ar.list.size(), ar.list), ar.expec));
	}

	public static int sockMerchant(int n, List<Integer> ar) {
		List<Integer> arClone = new ArrayList<Integer>(ar);
		int ret = 0;
		while (!arClone.isEmpty()) {
			int i = arClone.remove(0);
			arClone.removeAll(Collections.singleton(i));
			ret += Collections.frequency(ar, i) / 2;
		}
		return ret;
	}

	private static Ar createAr(int expec, String value) {
		return new Ar(expec, Stream.of(value.split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
	}

	static class Ar {
		List<Integer> list;
		int expec;

		Ar(int expec, List<Integer> list) {
			this.list = list;
			this.expec = expec;
		}
	}

}
