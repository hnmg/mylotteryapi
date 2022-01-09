package com.mylottery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.mylottery.ParamDto;
import com.mylottery.combinacao.Combination;

@RestController
public class LotteryController {

	@GetMapping("q")
	public String getCombinations(ParamDto dto) {
		String result = "";
		
		String n = dto.getN();
		String k = dto.getK();
		
		try {
			
			if(Strings.isNullOrEmpty(k) || Strings.isNullOrEmpty(n)) {
				return "Necessary informations not provided.  ?k=2&n=01,02,03";
			}
			
			int kCombinations = Integer.parseInt(k);
			String[] nElements = n.split(",");
			
			if(kCombinations > nElements.length) {
				return "Number of k(combinations) is greater than number os n(elements) provided.";
			}
			
			Combination combination = new Combination(nElements, kCombinations);
			String saida[];
			
			StringBuilder combinations = new StringBuilder();
			int count = 0;
			while(combination.hasNext()) {
				count++;
				saida = combination.nextCombination();
				String linha = String.join(",", saida);
				
				combinations.append(linha);
				combinations.append("</br>");
			}
			
			result += String.format("Total combinations: %d </p>", count);
			result += combinations.toString();
						
			System.out.println();
		}catch(Exception ex) {
			result = "Ooops: " + ex.getMessage();
			ex.printStackTrace();
		}
		
		return result;
	}
	
}
