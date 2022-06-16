import { serve } from "https://deno.land/std@0.120.0/http/server.ts";
import { configure } from "https://deno.land/x/eta@v1.12.3/mod.ts";
import * as listController from "./controllers/listController.js";
import * as statisticsController from "./controllers/statisticsController.js";
import * as itemController from "./controllers/itemController.js";

configure({
  views: `${Deno.cwd()}/views/`,
});

const handleRequest = async (request) => {
  const url = new URL(request.url);

  if (url.pathname === "/" && request.method === "GET") {
    return await statisticsController.viewStatistics(request);
  } else if (url.pathname === "/lists" && request.method === "POST") {
    return await listController.addList(request);
  } else if (url.pathname === "/lists" && request.method === "GET") {
    return await listController.viewLists(request);
  } else if (
    url.pathname.match("lists/[0-9]+/deactivate") && request.method === "POST"
  ) {
    return await listController.deactivateList(request);
  } else if (
    url.pathname.match("/lists/[0-9]+/items/[0-9]+/collect") &&
    request.method === "POST"
  ) {
    return await itemController.collectItem(request);
  } else if (
    url.pathname.match("lists/[0-9]+/items") && request.method === "POST"
  ) {
    return await itemController.addItem(request);
  } else if (url.pathname.match("lists/[0-9]+") && request.method === "GET") {
    return await itemController.viewItems(request);
  } else {
    return new Response("Not found", { status: 404 });
  }
};

serve(handleRequest, { port: 7777 });
