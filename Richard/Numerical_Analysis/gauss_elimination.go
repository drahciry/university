package main

import "fmt"

func gaussElimination(system [][]float64, n int) []float64 {
	for k := 0; k < n; k++ {
		for i := k + 1; i < n; i++ {
			m := system[i][k] / system[k][k]
			for j := k; j <= n; j++ {
				system[i][j] = system[i][j] - (m * system[k][j])
			}
		}
	}

	xn := make([]float64, n, n)

	for i := n - 1; i >= 0; i-- {
		xn[i] = system[i][n]

		for j := i + 1; j < n; j++ {
			xn[i] = xn[i] - (system[i][j] * xn[j])
		}

		xn[i] = xn[i] / system[i][i]
	}

	return xn
}

func main() {
	var n int
	fmt.Print("What is the size of linear system?\nR: ")
	fmt.Scanln(&n)
	fmt.Println("")

	var system [][]float64 = make([][]float64, n, n)
	for i := 0; i < n; i++ {
		system[i] = make([]float64, n+1, n+1)
	}

	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			fmt.Printf("Equation %d: x[%d] = ", i+1, j+1)
			fmt.Scanln(&system[i][j])
		}
		fmt.Printf("Equation %d: b = ", i+1)
		fmt.Scanln(&system[i][n])
		fmt.Println("")
	}

	solution := gaussElimination(system, n)

	for i := 0; i < n; i++ {
		fmt.Printf("x[%d] = %.8f\n", i, solution[i])
	}
}
