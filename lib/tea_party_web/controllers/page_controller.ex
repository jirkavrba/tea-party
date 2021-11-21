defmodule TeaPartyWeb.PageController do
  use TeaPartyWeb, :controller

  def index(conn, _params) do
    render(conn, "index.html")
  end

  def template(conn, %{"template" => template}) do
    render(conn, "#{template}.html")
  end
end
