const std = @import("std");
const coffee_shop = @import("coffee_shop");
const zap = @import("zap");

pub fn main() !void {
    std.debug.print("Hello Na-chan from Zig server\n", .{});

    var listener = zap.HttpListener.init(.{
        .port = 3000,
        .on_request =  on_request,
        .log = true,
    });
    try listener.listen();

    std.debug.print("Listening on localhost \n", .{});

    //start worker thread
    zap.start(.{
        .threads = 2,
        .workers = 2,
    });

    try coffee_shop.bufferedPrint();
}

fn on_request(r: zap.Request) !void {
     if (r.path) |the_path| {
        std.debug.print("PATH: {s}\n", .{the_path});
    }

    if (r.query) |the_query| {
        std.debug.print("QUERY: {s}\n", .{the_query});
    }
    r.sendBody("<html><body><h1>Hello Na-chan from with love!!</h1></body></html>") catch return;
}
