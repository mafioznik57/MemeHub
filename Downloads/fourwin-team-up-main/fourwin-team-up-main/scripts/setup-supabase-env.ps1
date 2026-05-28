# Fetches Supabase API keys via CLI and writes .env (requires: npx supabase login first)
$ErrorActionPreference = "Stop"
$ProjectRef = "hsgcjknghqsjtfonqkbk"
$Root = Split-Path -Parent $PSScriptRoot
Set-Location $Root

Write-Host "Checking Supabase CLI login..."
$keysJson = npx supabase projects api-keys --project-ref $ProjectRef -o json 2>&1
if ($LASTEXITCODE -ne 0) {
  Write-Host ""
  Write-Host "Not logged in. Run:" -ForegroundColor Yellow
  Write-Host "  npx supabase login"
  Write-Host "Then run this script again."
  exit 1
}

$keys = $keysJson | ConvertFrom-Json
$anon = ($keys | Where-Object { $_.name -eq "anon" -or $_.id -eq "anon" }).api_key
if (-not $anon) {
  $anon = ($keys | Select-Object -First 1).api_key
}
if (-not $anon) {
  Write-Host "Could not find anon key in CLI output." -ForegroundColor Red
  exit 1
}

$url = "https://${ProjectRef}.supabase.co"
$envContent = @"
VITE_SUPABASE_URL=$url
VITE_SUPABASE_PUBLISHABLE_KEY=$anon
SUPABASE_URL=$url
SUPABASE_PUBLISHABLE_KEY=$anon
"@

Set-Content -Path ".env" -Value $envContent.TrimEnd() -Encoding utf8
Write-Host "Wrote .env with Supabase URL and anon key." -ForegroundColor Green
Write-Host "Run: npm run dev"
