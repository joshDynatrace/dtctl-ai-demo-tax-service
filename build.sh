#!/usr/bin/env bash
set -euo pipefail

docker buildx build --platform linux/amd64,linux/arm64 -t tax-service:latest .

# example to build and push:
# docker buildx build --platform linux/amd64,linux/arm64 -t jhendrick/tax-service:latest . --push