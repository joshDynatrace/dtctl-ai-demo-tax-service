# Tax Service

A Spring Boot microservice that returns tax rates for a given product tax code and shipping state. Deployed in its own Kubernetes namespace (`tax-service`) separately from the arc-store.

## The role it plays in the demo

The arc-store backend calls this service on every order to calculate tax. The Arc Display product has no tax code configured, so this service returns `taxRate: null` for it. That null causes a `NullPointerException` in the arc-store backend — the bug the Dynatrace Live Debugger is used to diagnose.

## Project layout

```
tax-service/
├── src/                  Spring Boot application
├── k8s/                  Kubernetes manifests (namespace: tax-service)
├── Dockerfile
└── build.sh              Build Docker image
```

## API

```
GET /api/tax-rate?taxCode={taxCode}&state={state}
```

Returns:

```json
{ "taxCode": "ELECTRONICS", "state": "CA", "taxRate": 0.0725 }
```

Returns `taxRate: null` for any unrecognised tax code.

## Known tax codes

| Tax Code        | Description      |
|-----------------|------------------|
| ELECTRONICS     | Consumer electronics |
| AUDIO_EQUIPMENT | Headphones, speakers |

Any other tax code (including `null`) returns `taxRate: null`.

## Build Docker image

Run from the `tax-service/` directory:

```bash
./build.sh
```

Builds for `linux/amd64` and `linux/arm64` using `docker buildx`.

## Deploy to Kubernetes

```bash
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/tax-service.yaml
```

## Local development

```bash
mvn spring-boot:run
```

Runs on port `8081`.
