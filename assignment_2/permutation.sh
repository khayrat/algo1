#!/bin/bash
set -Eeuo pipefail

dir=$(cd $(dirname ${BASH_SOURCE[0]}) && pwd)
java -cp /home/glende/.m2/repository/edu/princeton/cs/algs4/1.0.0.0/algs4-1.0.0.0.jar:$dir/target/classes Permutation $1 < $2
