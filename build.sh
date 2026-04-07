#!/usr/bin/env bash
set -euo pipefail

docker buildx build --platform linux/amd64,linux/arm64 -t tax-service:latest .
