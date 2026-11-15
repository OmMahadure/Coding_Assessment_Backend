
package com.main.service;

import org.springframework.stereotype.Service;

@Service
public class CodeTemplates {

	public String getTemplate(String language) {

		return switch (language.toUpperCase()) {

		case "CPP" -> """
				#include <bits/stdc++.h>
				using namespace std;

				int main() {
				    ios::sync_with_stdio(false);
				    cin.tie(nullptr);

				    // Write your code here

				    return 0;
				}

								                """;

		case "JAVA" -> """
				import java.util.*;

				public class Main {
				    public static void main(String[] args) {

				        Scanner sc = new Scanner(System.in);

				        // Write your code here

				        sc.close();
				    }
				}
				""";

		case "PYTHON" -> """
				import sys

				def main():
				
				    # Write your code here
				    
				    pass

				if __name__ == "__main__":
				    main()
				""";

		default -> throw new IllegalArgumentException("Unsupported programming language: " + language);
		};
	}
}
