import Config

# We don't run a server during test. If one is required,
# you can enable the server option below.
config :tea_party, TeaPartyWeb.Endpoint,
  http: [ip: {127, 0, 0, 1}, port: 4002],
  secret_key_base: "MpGvBCL0xSZn1Ym+vpXKgLoyfj7VAUgV7et1U1ji6m+9Mi/se4RSQ9ZaXYHrFtRl",
  server: false

# Print only warnings and errors during test
config :logger, level: :warn

# Initialize plugs at runtime for faster test compilation
config :phoenix, :plug_init_mode, :runtime
